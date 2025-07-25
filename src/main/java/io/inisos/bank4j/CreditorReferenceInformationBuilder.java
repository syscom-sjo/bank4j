package io.inisos.bank4j;

import iso.std.iso._20022.tech.xsd.pain_001_001.DocumentType3Code;

public interface CreditorReferenceInformationBuilder {

    CreditorReferenceInformationBuilder code(DocumentType3Code code);

    CreditorReferenceInformationBuilder issuer(String issuer);

    CreditorReferenceInformationBuilder ref(String ref);

    CreditorReferenceInformation build();
}
