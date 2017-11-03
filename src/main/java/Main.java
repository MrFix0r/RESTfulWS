import com.bivgroup.UniServiceImpl;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class Main extends Application {

    private Set<Object> singletons = new HashSet<Object>();

    public Main() {
        singletons.add(new UniServiceImpl());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

}