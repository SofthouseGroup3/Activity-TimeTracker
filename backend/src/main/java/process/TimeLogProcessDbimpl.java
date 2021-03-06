package process;

import db.TimeLogDAO;
import db.entity.TimeLog;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("deprecation")
public class TimeLogProcessDbimpl implements TimeLogProcess {
    private TimeLogDAO timelogDAO;

    public TimeLogProcessDbimpl(TimeLogDAO timelogDAO) { this.timelogDAO = timelogDAO; }

    @Override
    public List<TimeLog> list() { return this.timelogDAO.list(); }

    @Override
    public TimeLog create(TimeLog timelog) { return this.timelogDAO.findByTime(this.timelogDAO.create(timelog)); }

    @Override
    public TimeLog update(Integer timeID, TimeLog updatedTimeLog) throws NotFoundException
    {
        TimeLog timelog = this.findTime(timeID);

        timelog.setTime(updatedTimeLog.getTime());
        timelog.setDate(updatedTimeLog.getDate());
        timelog.setTitle(updatedTimeLog.getTitle());
        timelog.setUsername(updatedTimeLog.getUsername());
        
        this.timelogDAO.update(timelog);

        return timelog;
    }

    @Override
    public TimeLog findTime(Integer timeID) throws NotFoundException {
        return Optional
                .ofNullable(this.timelogDAO.findByTime(timeID))
                .orElseThrow(() -> new NotFoundException("timelog doesnt exist"));
    }

    @Override
    public TimeLog addTime(String title ) throws NotFoundException {
        return this.timelogDAO.time(title);
    }


    @Override
    public List<TimeLog> findTimelog(String username)
    {
        return this.timelogDAO.listofuser(username);

    }


    @Override
    public List<TimeLog> findActivity(String title)
    {
        return this.timelogDAO.listofactivity(title);

    }
    @Override
    public void delete(Integer timeID) {
        this.timelogDAO.deleteByTime(timeID);
    }

    /*
    @Override
    public TimeLog findActivity(Integer activityID) throws NotFoundException
    {
        return Optional
                .ofNullable(this.timelogDAO.findByActivity(activityID))
                .orElseThrow(() -> new NotFoundException("activity does not exist"));
    }
  */



  /*  @Override
    public TimeLog findTimelog(Integer userId, Integer activityID)
    {
        return this.timelogDAO.findingtimelog(userId, activityID);

    }
*/

/*
    @Override
    public List<TimeLog> findTimelog(Integer userId)
    {
        return this.timelogDAO.findingtimelog(userId);

    }
*/
/*
    @Override
    public TimeLog findTimelog(Integer userId, Integer activityID)
    {
        return this.timelogDAO.findingtimelog(userId, activityID);

    }
*/

   /* @Override
    public TimeLog findUser(Integer userId) throws NotFoundException
    {
        return Optional
                .ofNullable(this.timelogDAO.findByUser(userId))
                .orElseThrow(() -> new NotFoundException(" user does not exist"));

    }
  */

}