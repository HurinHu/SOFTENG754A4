import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Database {
    List<Concession> _concessionsPool = new ArrayList<>();

    public Database(){

    }

    public void setConcessionPool(Concession concession) {
        _concessionsPool.add(concession);
    }

    public List<Concession> getConcessionPool() {
        return _concessionsPool;
    }
}
