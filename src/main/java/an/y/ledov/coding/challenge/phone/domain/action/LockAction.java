package an.y.ledov.coding.challenge.phone.domain.action;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import static java.util.Objects.isNull;

@Component
public class LockAction {

    private final Map<String, Object> locks = new HashMap<>();

    private synchronized void prepareLock(String lockId) {
        if (isNull(locks.get(lockId))) {
            locks.put(lockId, new Object());
        }
    }

    /**
     * This approach works for the one pod only.
     * In case of multiple pods, we need to use distributed lock for an instance in MongoDB itself.
     */
    public <F, P, T> T withLock(String lockId,
                                F repository,
                                P entity,
                                BiFunction<F, P, T> process) {
        prepareLock(lockId);

        synchronized (locks.get(lockId)) {
            return process.apply(repository, entity);
        }
    }

    //TODO: implement distributed lock
    private <F, P, T> T withDistributedLock(String lockId,
                                F repository,
                                P entity,
                                BiFunction<F, P, T> process) {

        return null;
    }
}
