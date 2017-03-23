package ultimate.converter;

import java.util.ArrayList;
import java.util.List;

public class Field {
	String line;
	Integer size;
	FieldType type;
	List<Field> innerFields = new ArrayList<>();
	DataField data;
	
	public String getLine() {
		return line;
	}


	public Integer getSize() {
		return size;
	}


	public FieldType getType() {
		return type;
	}


	public List<Field> getInnerFields() {
		return innerFields;
	}


	public DataField getData() {
		return data;
	}


	public static enum FieldType {
		SUPER_VAR, OCCURS, REDEFINE, FILLER, CLASIC  
	}
	
	
	public static class Builder extends Field{
		
		public static Builder getInstance() {
			return new Builder();
		}
		
		public Builder addLine(String line) {
			this.line = line;
			return this;
		}
		
		public Builder addSize(Integer size) {
			this.size = size;
			return this;
		}
		
		public Builder addInnerField(Field field) {
			this.innerFields.add(field);
			return this;
		}
		
		public Builder addType(FieldType type) {
			this.type = type;
			return this;
		}
		
		public Builder addOccurData(Integer occurs) {
			this.type = FieldType.OCCURS;
			this.data = new OccursDataField(occurs);
			return this;
		}
		
		public Field build() {
			return this;
		}
	}
}
