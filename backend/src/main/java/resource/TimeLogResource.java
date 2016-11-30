package resource;

import db.entity.TimeLog;
import javassist.NotFoundException;
import process.TimeLogProcess;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Path("/timelog")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TimeLogResource {

    private TimeLogProcess timelogProcess;

    public  TimeLogResource (TimeLogProcess timelogProcess)
    { this.timelogProcess = checkNotNull(timelogProcess);}

    @GET
    public List<TimeLog> timeLogList() {
        return this.timelogProcess.list();}


   /* @PermitAll
    @GET
    public List<TimeLog> advancelist(@QueryParam("userId") Integer userId)
    {
            return this.timelogProcess.findTimelog(userId);
    }*/

    @GET
    @Path("/{userId}")
    public List<TimeLog> timeloguser( @PathParam("userId")Integer userId)
    {
        return this.timelogProcess.findTimelog(userId);
    }

/*
    @POST
    @Path("/list")
    public List<TimeLog> timeloguser( TimeLog timelog)
    {
        return this.timelogProcess.findTimelog(timelog);
    }
*/

    @POST
    public TimeLog createTimeLog(TimeLog timelog)
    {
        return this.timelogProcess.create(timelog);
    }

    @PUT
    @Path("/{timeID}")
    public TimeLog updateTimeLog(@PathParam("timeID") Integer timeID,@Valid TimeLog timelog) throws NotFoundException {
        return this.timelogProcess.update(timeID, timelog);}

    @DELETE
    @Path("/{timeID}")
    public void deleteTimeLog ( @PathParam("timeID") Integer timeID) {
        this.timelogProcess.delete(timeID);
    }
}

