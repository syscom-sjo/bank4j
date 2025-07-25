package io.inisos.bank4j;

import iso.std.iso._20022.tech.xsd.pain_001_001.DocumentType3Code;

/**
 * CreditorReferenceInformation
 */
public interface CreditorReferenceInformation {

    /**
     * @return type code
     */
    DocumentType3Code getCode();

    /**
     * @return type issuer
     */
    String getIssuer();

    /**
     * @return reference
     */
    String getRef();

}
