package nl.futureedge.simple.jta;

/**
 * System callback; not for external use!
 */
public interface JtaSystemCallback {

    /**
     * Callback when the transaction is completed.
     * @param transaction the completed transaction
     */
    void transactionCompleted(JtaTransaction transaction);
}
