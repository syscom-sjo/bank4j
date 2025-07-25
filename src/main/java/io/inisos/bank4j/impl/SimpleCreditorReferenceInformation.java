package io.inisos.bank4j.impl;

import java.util.Objects;

import io.inisos.bank4j.CreditorReferenceInformation;
import io.inisos.bank4j.validator.constraints.Iso20022CharacterSet;
import iso.std.iso._20022.tech.xsd.pain_001_001.DocumentType3Code;
import jakarta.validation.constraints.Size;

public class SimpleCreditorReferenceInformation implements CreditorReferenceInformation {
    private final DocumentType3Code code;

    @Size(min = 1, max = 35)
    @Iso20022CharacterSet
    private final String issuer;

    @Size(min = 1, max = 35)
    @Iso20022CharacterSet
    private final String ref;

    public SimpleCreditorReferenceInformation(DocumentType3Code code, String issuer, String ref) {
        this.code =  Objects.requireNonNull(code, "Code cannot be null");
        this.issuer =  Objects.requireNonNull(issuer, "Issuer cannot be null");
        this.ref =  Objects.requireNonNull(ref, "Ref cannot be null");
    }

    public DocumentType3Code getCode() {
        return code;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getRef() {
        return ref;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, issuer, ref);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SimpleCreditorReferenceInformation other = (SimpleCreditorReferenceInformation) obj;
        return code == other.code && Objects.equals(issuer, other.issuer) && Objects.equals(ref, other.ref);
    }

    @Override
    public String toString() {
        return "SimpleCreditorReferenceInformation [code=" + code + ", issuer=" + issuer + ", ref=" + ref + "]";
    }
}
