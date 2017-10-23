package nl.futureedge.simple.jta.xa;

import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * XA Resource adapter that contains the name of the resource manager.
 */
public class XAResourceAdapter implements XAResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(XAResourceAdapter.class);

    private final String resourceManager;
    private final boolean supportsJoin;

    private final XAResource xaResource;

    public XAResourceAdapter(final String resourceManager, final boolean supportsJoin, final XAResource xaResource) {
        this.resourceManager = resourceManager;
        this.supportsJoin = supportsJoin;
        this.xaResource = xaResource;
    }

    public String getResourceManager() {
        return resourceManager;
    }

    public boolean supportsJoin() {
        return supportsJoin;
    }

    @Override
    public void commit(Xid xid, boolean onePhase) throws XAException {
        LOGGER.trace("commit(xid={},onePhase={})", xid, onePhase);
        xaResource.commit(xid, onePhase);
    }

    @Override
    public void end(Xid xid, int flags) throws XAException {
        LOGGER.trace("end(xid={},flags={})", xid, flags);
        xaResource.end(xid, flags);
    }

    @Override
    public void forget(Xid xid) throws XAException {
        LOGGER.trace("forget(xid={})", xid);
        xaResource.forget(xid);
    }

    @Override
    public int getTransactionTimeout() throws XAException {
        LOGGER.trace("getTransactionTimeout()");
        return xaResource.getTransactionTimeout();
    }

    @Override
    public boolean isSameRM(XAResource otherResource) throws XAException {
        LOGGER.trace("isSameRM(otherResource={})", otherResource);
        // Unwrap by calling on 'other' side
        return otherResource.isSameRM(this.xaResource);
    }

    @Override
    public int prepare(Xid xid) throws XAException {
        LOGGER.trace("prepare(xid={})", xid);
        return xaResource.prepare(xid);
    }

    @Override
    public Xid[] recover(int flag) throws XAException {
        LOGGER.trace("recover(flag={})", flag);
        return xaResource.recover(flag);
    }

    @Override
    public void rollback(Xid xid) throws XAException {
        LOGGER.trace("rollback(xid={})", xid);
        xaResource.rollback(xid);
    }

    @Override
    public boolean setTransactionTimeout(int seconds) throws XAException {
        LOGGER.trace("setTransactionTimeout(seconds={})", seconds);
        return xaResource.setTransactionTimeout(seconds);
    }

    @Override
    public void start(Xid xid, int flags) throws XAException {
        LOGGER.trace("start(xid={},flags={})", xid, flags);
        xaResource.start(xid, flags);
    }

    @Override
    public String toString() {
        return "XAResourceAdapter{" +
                "resourceManager='" + resourceManager + '\'' +
                ", xaResource=" + xaResource +
                '}';
    }
}
