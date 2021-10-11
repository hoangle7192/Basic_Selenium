package Method_In_Java;

import org.openqa.selenium.json.JsonOutput;
import org.testng.annotations.Test;

import java.util.Date;

public class getDateTimeNow {

    public void a() {
        System.out.println("datetime =" + DateTimeNow());
    }
    public String DateTimeNow() {
        Date date = new Date();
        return date.toString();

        // new Data().toString();
    }
}
