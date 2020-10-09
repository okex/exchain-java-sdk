package com.okexchain.msg.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.SerializedName;
import com.okexchain.msg.common.CommissionMsg;
import com.okexchain.msg.common.Description;
import com.okexchain.msg.common.Token;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class MsgCreateOKValidatorValue {

    @JsonProperty("delegator_address")
    @SerializedName("delegator_address")
    private String delegatorAddress;

    private Description description;

    @JsonProperty("min_self_delegation")
    @SerializedName("min_self_delegation")
    private Token minSelfDelegation;

    private String pubkey;

    @JsonProperty("validator_address")
    @SerializedName("validator_address")
    private String validatorAddress;

    public void setPubKey(String pubKeyString) {
        this.pubkey= pubKeyString;
    }

    public void setDelegatorAddress(String delegatorAddress) {
        this.delegatorAddress = delegatorAddress;
    }

    public void setValidatorAddress(String validatorAddress) {
        this.validatorAddress = validatorAddress;
    }

    public void setMinSelfDelegation(Token minSelfDelegation) {
        this.minSelfDelegation = minSelfDelegation;
    }

    public void setDescription(Description description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("delegator_address", delegatorAddress)
                .append("validator_address", validatorAddress)
                .append("min_self_delegation", this.minSelfDelegation)
                .append("description", this.description)
                .append("pubkey", this.pubkey)
                .toString();
    }

}
