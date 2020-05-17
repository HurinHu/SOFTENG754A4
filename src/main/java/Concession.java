import java.text.SimpleDateFormat;
import java.util.Date;

public class Concession {
    private User _user;
    private Course _course;
    private ConcessionStatus _status;
    private String _date;

    public Concession(User user, Course course) {
        _user = user;
        _course = course;
        _status = ConcessionStatus.concession_waiting;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        _date = formatter.format(date);
    }

    public ConcessionStatus getStatus(){
        return _status;
    }

    public void setStatus(ConcessionStatus status) {
        this._status = status;
    }
}
