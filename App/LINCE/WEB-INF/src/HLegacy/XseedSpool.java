package HLegacy;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.print.*;
import javax.print.*;
public class XseedSpool implements Printable
{
	 String buffer[];
	 String font;
     int fontSize;
     
	public XseedSpool(String buffer[], String font, int fontSize)
	{
		this.buffer = buffer;
		this.font = font;
		this.fontSize = fontSize;
	}
	
	public XseedSpool(String buffer[])
	{
		this.buffer = buffer;
		this.font = "Courier New";
		this.fontSize = 8;
	}
	
	public int print(Graphics g, PageFormat format, int pageIndex)
	{
	 // Text Font
        Font textFont = new Font(font, Font.PLAIN, fontSize);
     // Text Coordinates
        Point2D.Float   pen = new Point2D.Float();

        Graphics2D g2d = (Graphics2D) g;
        
     // Move the origin from the corner of the Paper to the corner
     // of the imageable area.
        g2d.translate(format.getImageableX(), format.getImageableY());
        
     // Text Color
        g2d.setPaint (Color.black);

        for(int i=0; (i < buffer.length && buffer[i] != null); i++)
        {
       		TextLayout layout = new TextLayout(buffer[i], textFont, g2d.getFontRenderContext());         
            
            pen.y = pen.y + layout.getAscent();
            layout.draw(g2d, pen.x, pen.y);
            pen.y = pen.y + layout.getDescent() + layout.getLeading();
        }
        
        return(Printable.PAGE_EXISTS);        
	}
	
}