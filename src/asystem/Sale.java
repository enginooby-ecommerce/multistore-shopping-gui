package asystem;

public enum Sale {
	//type 1: percent
	S25("Sale 25%", 25, 1), S50("Sale 50%", 50, 1), S80("Sale 80%", 80, 1), B2G1("Buy 2 get 1", 3 / 2, 2),
	//type 2: buy x get y
	B3G1("Buy 3 get 1", 4 / 3, 2), B3G2("Buy 3 get 2", 5 / 3, 2);

	private String content;
	private double num;
	private int type;

	Sale(String content, double num, int type) {
		this.content = content;
		this.num = num;
		this.type = type;
	}

	public static Sale getSaleByContent(String content){
	    switch (content) {
	    case "Sale 25%": return Sale.S25;
	    case "Sale 50%": return Sale.S50;
	    case "Sale 80%": return Sale.S80;
	    case "Buy 2 get 1": return Sale.B2G1;
	    case "Buy 3 get 1": return Sale.B3G1;
	    case "Buy 3 get 2": return Sale.B3G2;
	    default: return null;
	    }
	}
	
	public String getContent() {
		return content;
	}

	public double getNum() {
		return num;
	}

	public int getType() {
		return type;
	}
	
}
