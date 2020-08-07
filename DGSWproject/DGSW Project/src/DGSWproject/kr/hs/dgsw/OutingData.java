package DGSWproject.kr.hs.dgsw;

public class OutingData {
	public int num;
	public int no;
	public int stmon;
	public int stday;
	public int sthour;
	public int stmin;
	public int endmon;
	public int endday;
	public int endhour;
	public int endmin;
	public String reason;
	
	public void set(int num, int no, int stmon, int stday, int sthour,int stmin, int endmon, int endday, int endhour, int endmin, String reason){
		this.num = num;
		this.no = no;
		this.stmon = stmon;
		this.stday = stday;
		this.sthour = sthour;
		this.stmin = stmin;
		this.endmon = endmon;
		this.endday = endday;
		this.endhour = endhour;
		this.endmin = endmin;
		this.reason = reason;
	}
}
