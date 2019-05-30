package io.okchain.types;

/**
 * Created by oker on 2019/5/28.
 */
public class AccountInfo extends AddressInfo {


    private String accountNumber;
    private String sequenceNumber;

    public AccountInfo(String privateKey, String publicKey, String userAddress, String accountNumber, String sequenceNumber) {
        super(privateKey, publicKey, userAddress);
        this.accountNumber = accountNumber;
        this.sequenceNumber = sequenceNumber;
    }

    public AccountInfo(AddressInfo addressInfo, String accountNumber, String sequenceNumber) {
        super(addressInfo.getPrivateKey(), addressInfo.getPublicKey(), addressInfo.getUserAddress());
        this.accountNumber = accountNumber;
        this.sequenceNumber = sequenceNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

}
