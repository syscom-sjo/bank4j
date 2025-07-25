package io.inisos.bank4j.util;

import io.inisos.bank4j.CreditorReferenceInformation;
import iso.std.iso._20022.tech.xsd.pain_001_001.DocumentType3Code;

public class CreditorReferenceInformationValidator {

    private CreditorReferenceInformationValidator() {
        // Prevent instantiation
    }

    public static boolean isValid(CreditorReferenceInformation value) {
        try {
            validate(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static void validate(CreditorReferenceInformation value){
        if (value.getCode() == null || value.getIssuer() == null || value.getRef() == null)
            throw new IllegalArgumentException("Code, Issuer, and Ref cannot be null");

        if(value.getIssuer().isEmpty() || value.getIssuer().length() > 35)
            throw new IllegalArgumentException("Issuer must be between 1 and 35 characters");

        if(value.getRef().isEmpty() || value.getRef().length() > 35)
            throw new IllegalArgumentException("Ref must be between 1 and 35 characters");

        if(value.getCode() == DocumentType3Code.SCOR){
            if (value.getIssuer().equals("BBA") && !isValidBBA(value.getRef())) {
                throw new IllegalArgumentException("Invalid BBA reference: " + value.getRef());
            } else if (value.getIssuer().equals("ISO") && !isvalidISO(value.getRef())) {
                throw new IllegalArgumentException("Invalid ISO reference: " + value.getRef());
            } // Other issuers are considered valid
        }
    }

    private static boolean isValidBBA(String ref) {
        if (ref == null || !ref.matches("\\d{12}"))
            return false;

        int base = Integer.parseInt(ref.substring(0, 10));
        int check = Integer.parseInt(ref.substring(10, 12));

        // mod 97 check
        int mod = base % 97;
        mod = mod == 0 ? 97 : mod;
        return mod == check;
    }

    private static boolean isvalidISO(String ref) {
        if (ref == null || !ref.matches("RF\\d{2}[A-Z0-9]{1,21}"))
            return false;

        // place first 4 characters at the back
        String rearranged = ref.substring(4) + ref.substring(0, 4);

        // convert alpha to numeric by ISO 11649 specification (A=10, B=11, ..., Z=35)
        StringBuilder numericString = new StringBuilder();
        for (char ch : rearranged.toCharArray()) {
            if (Character.isDigit(ch)) {
                numericString.append(ch);
            } else if (Character.isLetter(ch)) {
                numericString.append(Character.getNumericValue(ch)); // A-Z → 10-35
            } else {
                return false; // invalid characters
            }
        }

        // mod 97 check
        try {
            java.math.BigInteger bigInt = new java.math.BigInteger(numericString.toString());
            return bigInt.mod(java.math.BigInteger.valueOf(97)).intValue() == 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
