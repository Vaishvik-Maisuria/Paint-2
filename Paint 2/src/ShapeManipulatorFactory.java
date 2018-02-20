

public class ShapeManipulatorFactory {
	public static ShapeManipulatorStrategy create(String strategyName){
		ShapeManipulatorStrategy strategy=null;
		if(strategyName=="Circle"){
			strategy=new CircleManipulatorStrategy();
		} else if(strategyName=="Squiggle"){
			strategy=new SquiggleManipulatorStrategy();
		} else if(strategyName=="Rectangle"){
			strategy=new RectangleManipulatorStrategy();
		}else if(strategyName=="Square"){
			strategy=new SquareManipulatorStrategy();	
		} else if(strategyName=="Line"){
			strategy=new LineManipulatorStrategy(); 
		}else if(strategyName=="Triangle"){
			strategy=new TriangleManipulatorStrategy();
		}else if(strategyName=="Polygon"){
			strategy=new PolygonManipulatorStrategy(4);
		}
		return strategy;
	}
}
