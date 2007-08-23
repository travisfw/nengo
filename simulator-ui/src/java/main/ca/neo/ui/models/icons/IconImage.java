package ca.neo.ui.models.icons;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.net.URL;

import ca.neo.ui.style.Style;
import ca.shu.ui.lib.util.UIEnvironment;
import edu.umd.cs.piccolo.nodes.PImage;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;

/**
 * Just like PImage, except it semantically zooms (ie. at low scales, it does
 * not paint its bitmap)
 * 
 * @author Shu
 * 
 */
public class IconImage extends PImage {

	public static final boolean ENABLE_SEMANTIC_ZOOM = false;
	private static final long serialVersionUID = 1L;

	private static final Ellipse2D.Float TEMP_ELLIPSE = new Ellipse2D.Float();

	private transient GeneralPath path;

	PBounds originalBounds;

	double prevScale = 0;

	public IconImage() {
		super();
		init();
	}

	public IconImage(Image arg0) {
		super(arg0);
		init();
	}

	public IconImage(String arg0) {
		super(arg0);
		init();
	}

	public IconImage(URL arg0) {
		super(arg0);
		init();
	}

	private void init() {
		path = new GeneralPath();
		originalBounds = getBounds();
	}

	private void updatePath(double scale) {
		double origWidth = originalBounds.getWidth();
		double origHeight = originalBounds.getHeight();
		double width = origWidth * scale;
		double height = origWidth * scale;
		double offsetX = (origWidth - width) / 2f;
		double offsetY = (origHeight - height) / 2f;

		path.reset();
		TEMP_ELLIPSE.setFrame(offsetX, offsetY, width, height);
		path.append(TEMP_ELLIPSE, false);

	}

	@Override
	protected void paint(PPaintContext aPaintContext) {
		double s = aPaintContext.getScale();

		Graphics2D g2 = aPaintContext.getGraphics();

		if (ENABLE_SEMANTIC_ZOOM && s < UIEnvironment.SEMANTIC_ZOOM_LEVEL) {
			if (s != prevScale) {
				double delta = 1 - ((UIEnvironment.SEMANTIC_ZOOM_LEVEL - s) / UIEnvironment.SEMANTIC_ZOOM_LEVEL);

				updatePath(1 / delta);
			}
			g2.setPaint(Style.COLOR_FOREGROUND);
			g2.fill(path);

			// g2.fill(getBoundsReference());

		} else {
			super.paint(aPaintContext);
		}

	}
}
