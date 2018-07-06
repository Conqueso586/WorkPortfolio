package lab08;

import java.text.DecimalFormat;

public class Box {
private double width;
private double height;
private double depth;
private boolean full;
private String boxIs;

public Box(double width, double height, double depth){
	full = false;
	this.width = width;
	this.height = height;
	this.depth = depth;
}

public void setWidth(double w){
	width = w;
	
}
public double getWidth(){
	return width;
	
}

public void setHeight(double h){
	height = h;
	
}
public double getHeight(){
	return height;
	
}
public void setDepth(double d){
	depth = d;
	
}
public double getDepth(){
	return depth;
	
}
public void setFull(boolean full){
	this.full = full;
	
}
public boolean getFull(){
	return full;
	
}
public double calcVolume(){
	double volume = width*height*depth;
	return volume;
}
public double calcSurfaceArea(){
	double SA= 2*(width*height + width*depth + height*depth);
	return SA;
}
public String toString(){
	if(full == true){
		boxIs = "A full";
	}
	else{
		boxIs = "An empty";
	}
	DecimalFormat dec = new DecimalFormat("#.00");
	return boxIs + " "+ dec.format(width)+ " x "+ dec.format(height)+" x "+ dec.format(depth) +" box.";
	
}
}
