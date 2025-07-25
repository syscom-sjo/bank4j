package io.inisos.bank4j.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import io.inisos.bank4j.CreditorReferenceInformation;
import io.inisos.bank4j.impl.SimpleCreditorReferenceInformationBuilder;
import iso.std.iso._20022.tech.xsd.pain_001_001.DocumentType3Code;

class CreditorReferenceInformationValidatorTest {

    @ParameterizedTest
    @MethodSource("provideCreditorReferenceInformationTestCases")
    void testCreditorReferenceInformationValidation(DocumentType3Code code, String issuer, String ref, Class<Throwable> throwableClass /*Throwable.class*/) {
        CreditorReferenceInformation creditorReferenceInformation =
                new SimpleCreditorReferenceInformationBuilder()
                        .code(code)
                        .issuer(issuer)
                        .ref(ref)
                        .build();

        if(throwableClass != null) {
            assertThrows(throwableClass, () -> CreditorReferenceInformationValidator.validate(creditorReferenceInformation));
            return;
        }else {
            assertDoesNotThrow(() -> CreditorReferenceInformationValidator.validate(creditorReferenceInformation));
        }
    }

    private static Stream<Arguments> provideCreditorReferenceInformationTestCases() {
        return Stream.of(
                Arguments.of(DocumentType3Code.SCOR, "BBA", "090933755493", null),
                Arguments.of(DocumentType3Code.SCOR, "BBA", "090933755494", IllegalArgumentException.class),
                Arguments.of(DocumentType3Code.SCOR, "BBA", "INVALIDFORMAT", IllegalArgumentException.class),
                Arguments.of(DocumentType3Code.SCOR, "ISO", "RF771234567890ABCDEF12345", null),
                Arguments.of(DocumentType3Code.SCOR, "ISO", "RF00539007547034", IllegalArgumentException.class),
                Arguments.of(DocumentType3Code.SCOR, "ISO", "RF18INVALIDFORMAT", IllegalArgumentException.class),
                Arguments.of(DocumentType3Code.SCOR, "ABC", "ABC", null),
                Arguments.of(DocumentType3Code.SCOR, "", "ABC", IllegalArgumentException.class),
                Arguments.of(DocumentType3Code.SCOR, "ABC", "", IllegalArgumentException.class),
                Arguments.of(DocumentType3Code.RADM, "", "", IllegalArgumentException.class)
        );
    }
}
