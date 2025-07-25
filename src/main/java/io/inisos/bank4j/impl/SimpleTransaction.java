package io.inisos.bank4j.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Currency;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;

import io.inisos.bank4j.BankAccount;
import io.inisos.bank4j.CreditorReferenceInformation;
import io.inisos.bank4j.Party;
import io.inisos.bank4j.Transaction;
import io.inisos.bank4j.validator.constraints.Iso20022CharacterSet;
import iso.std.iso._20022.tech.xsd.pain_001_001.ChargeBearerType1Code;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

/**
 * Simple Transaction
 *
 * @author Patrice Blanchardie
 */
public class SimpleTransaction implements Transaction {

    private final Party party;
    private final BankAccount account;
    private final BigDecimal amount;
    private final Currency currency;
    @Iso20022CharacterSet
    private final String endToEndId;
    @Iso20022CharacterSet
    private final String id;
    private final ChargeBearerType1Code chargeBearerCode;
    private final List<BankAccount> intermediaryAgents;
    private final Set<@Valid @Size(max = 140) String> remittanceInformationUnstructured;
    private final Set<CreditorReferenceInformation> remittanceInformationStructured;

    @SuppressWarnings("java:S107")
    public SimpleTransaction(Party party, BankAccount account, BigDecimal amount, Currency currency, String endToEndId, String id, ChargeBearerType1Code chargeBearerCode, List<BankAccount> intermediaryAgents, Set<String> remittanceInformationUnstructured, Set<CreditorReferenceInformation> remittanceInformationStructured) {
        this.party = party;
        this.account = Objects.requireNonNull(account, "Account cannot be null");
        this.amount = Objects.requireNonNull(amount, "Amount cannot be null");
        this.currency = Objects.requireNonNull(currency, "Currency cannot be null");
        this.endToEndId = Objects.requireNonNull(endToEndId, "End to end id cannot be null");
        this.id = id;
        this.chargeBearerCode = chargeBearerCode;
        this.intermediaryAgents = Optional.ofNullable(intermediaryAgents).orElse(Collections.emptyList());
        this.remittanceInformationUnstructured = Optional.ofNullable(remittanceInformationUnstructured).orElse(Collections.emptySet());
        this.remittanceInformationStructured = Optional.ofNullable(remittanceInformationStructured).orElse(Collections.emptySet());
        if (this.intermediaryAgents.size() > 3) {
            throw new IllegalArgumentException("Intermediary agents cannot be more than 3");
        }
    }

    @Override
    public Optional<Party> getParty() {
        return Optional.ofNullable(party);
    }

    @Override
    public BankAccount getAccount() {
        return account;
    }

    @Override
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String getCurrencyCode() {
        return currency.getCurrencyCode();
    }

    @Override
    public String getEndToEndId() {
        return endToEndId;
    }

    @Override
    public Optional<String> getId() {
        return Optional.ofNullable(id);
    }

    @Override
    public Optional<ChargeBearerType1Code> getChargeBearerCode() {
        return Optional.ofNullable(chargeBearerCode);
    }

    @Override
    public List<BankAccount> getIntermediaryAgents() {
        return intermediaryAgents;
    }

    @Override
    public Set<String> getRemittanceInformationUnstructured() {
        return remittanceInformationUnstructured;
    }

    @Override
    public Set<CreditorReferenceInformation> getRemittanceInformationStructured() {
        return remittanceInformationStructured;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleTransaction)) return false;
        SimpleTransaction that = (SimpleTransaction) o;
        return getParty().equals(that.getParty()) && getAccount().equals(that.getAccount()) && getAmount().equals(that.getAmount()) && currency.equals(that.currency) && getEndToEndId().equals(that.getEndToEndId()) && Objects.equals(getId(), that.getId()) && getChargeBearerCode().equals(that.getChargeBearerCode()) && getIntermediaryAgents().equals(that.getIntermediaryAgents()) && getRemittanceInformationUnstructured().equals(that.getRemittanceInformationUnstructured()) && getRemittanceInformationStructured().equals(that.getRemittanceInformationStructured());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParty(), getAccount(), getAmount(), currency, getEndToEndId(), getId(), getChargeBearerCode(), getIntermediaryAgents(), getRemittanceInformationUnstructured(), getRemittanceInformationUnstructured());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SimpleTransaction.class.getSimpleName() + "[", "]")
                .add("party=" + party)
                .add("account=" + account)
                .add("amount=" + amount)
                .add("currency=" + currency)
                .add("endToEndId='" + endToEndId + "'")
                .add("id='" + id + "'")
                .add("chargeBearerCode=" + chargeBearerCode)
                .add("intermediaryAgents=" + intermediaryAgents)
                .add("remittanceInformationUnstructured=" + getRemittanceInformationUnstructured())
                .add("remittanceInformationStructured=" + getRemittanceInformationStructured())
                .toString();
    }
}
