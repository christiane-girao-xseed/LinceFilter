package HLegacy;
import java.io.*;

public class XseedException 
{
public void sortException  (Process pProc) 
   throws  Exception
{
    String  line;
    String lineException;

    InputStreamReader inpread = new
    InputStreamReader(pProc.getErrorStream());
    BufferedReader input = new  BufferedReader(inpread);

    lineException =  "";
    while ((line = input.readLine()) !=  null)
    {
        lineException =  lineException + " " + line;
    }

    input.close();

    if  (lineException.equals("") ==  false)
        throw new  Exception(lineException);
}

}