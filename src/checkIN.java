import java.io.IOException;


public class checkIN {


    Process process = java.lang.Runtime.getRuntime().exec("ping www.google.com");
    int x = process.waitFor();

    public checkIN() throws InterruptedException, IOException {
    }


    public boolean checkIN() throws IOException, InterruptedException {
        if (x == 0) {
            return true;
        }
        else {
            return false;
        }
    }
}
