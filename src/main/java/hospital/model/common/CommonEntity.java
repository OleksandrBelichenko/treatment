package hospital.model.common;

import java.io.Serializable;

/**
 * @author Oleksandr Belichenko
 */
public interface CommonEntity<P extends Serializable> extends Serializable {

    P getId();

    void setId(P id);
}
