package heca;



public class Attribute{
	String attibuteName; //e.g WindPower
	double perUnitWeight; 
	int consumed;
	int   limit;
	
		public static class Builder {
	        //required
	        private  String attibuteName; 
	        //optional
	        private double perUnitWeight; 
	        private  int consumed;
	        private  int   limit;
	
	        public Builder(String size) {
	          this.attibuteName = size;
	        }
	
	        public Builder perUnitWeight(double value) {
	        	perUnitWeight = value;
	          return this;
	        }
	
	        public Builder consumed(int value) {
	        	consumed = value;
	          return this;
	        }
	
	        public Builder limit(int value) {
	        	limit = value;
	          return this;
	        }
	
	        public Attribute build() {
	          return new Attribute(this);
	        }
	    }

	private Attribute(Builder builder) {
		attibuteName = builder.attibuteName;
		perUnitWeight = builder.perUnitWeight;
		consumed = builder.consumed;
		limit = builder.limit;
	}
		
	public Attribute(String a){
		this(a, 0.0, 0, (int)1e6);
		
	}
	public Attribute(String a, double   w, int   m, int l){
		attibuteName = a;
		perUnitWeight = w;
		consumed  = m;
		limit = l;
		
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("\n\tattibute : "+this.attibuteName+"\tConsumed :"+ this.consumed + "\tLimit : "+ this.limit);		
		return sb.toString();	
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attibuteName == null) ? 0 : attibuteName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attribute other = (Attribute) obj;
		if (attibuteName == null) {
			if (other.attibuteName != null)
				return false;
		} else if (!attibuteName.equals(other.attibuteName))
			return false;
		return true;
	}	
}
