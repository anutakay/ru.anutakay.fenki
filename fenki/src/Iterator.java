
import ru.fenki.model.NodeAndThreadStorage;
import ru.fenki.model.NodeStoreDimensions;
import ru.fenki.service.Index;


public class Iterator extends Index{
	
	NodeStoreDimensions mDimensions;

	private Iterator(int i, int j) {
		super(i, j);
	}
	
	public Iterator(NodeAndThreadStorage storage){
		this(0, -1);
	mDimensions = storage.getDimensions();
	}
	
	public Iterator next(){
		j++;
		if(j>=mDimensions.getThreadNumber()){
			j=-1;
			i++;
			if(i >= mDimensions.getColumnNumber()*2+1){
				return null;
			}
		}
		return this;
	}

}
