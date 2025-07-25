package io.inisos.bank4j.impl;

import io.inisos.bank4j.CreditorReferenceInformation;
import io.inisos.bank4j.CreditorReferenceInformationBuilder;
import iso.std.iso._20022.tech.xsd.pain_001_001.DocumentType3Code;

public class SimpleCreditorReferenceInformationBuilder implements CreditorReferenceInformationBuilder {

    private DocumentType3Code code;

    private String issuer;

    private String ref;

    @Override
    public CreditorReferenceInformationBuilder code(DocumentType3Code code) {
        this.code = code;
        return this;
    }

    @Override
    public CreditorReferenceInformationBuilder issuer(String issuer) {
        this.issuer = issuer;
        return this;
    }

    @Override
    public CreditorReferenceInformationBuilder ref(String ref) {
        this.ref = ref;
        return this;
    }

    @Override
    public CreditorReferenceInformation build() {
        return new SimpleCreditorReferenceInformation(
                code,
                issuer,
                ref);
    }
}
