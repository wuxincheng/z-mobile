package lihu.zlm.util;

public class ColorUtil {

	private static String[] COLORS = new String[] { "f44336", // RED
			"#e91e63", // PINK
			"#9c27b0", // PURPLE
			"#673ab7", // DEEP PURPLE
			"#3f51b5", // INDIGO
			"#2196f3", // BLUE
			"#03a9f4", // LIGHT BLUE
			"#00bcd4", // CYAN
			"#009688", // TEAL
			"#4caf50", // GREEN
			"#8bc34a", // LIGHT GREEN
			"#cddc39", // LIME
			"#ffeb3b", // YELLOW
			"#ffc107", // AMBER
			"#ff9800", // ORANGE
			"#ff5722", // DEEP ORANGE
			"#795548", // BROWN
			"#9e9e9e", // GREY
			"#607d8b", // BLUE GREY
	};

	/**
	 * 随机产生颜色代码
	 * 
	 * @return
	 */
	public static String getBgColorRandom() {
		int index = (int) (Math.random() * COLORS.length);
		return COLORS[index];
	}

}
