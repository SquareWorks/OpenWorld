package com.squareworks.openworld.client.keyAction;

public enum KeyAction {
	up("Camera Up"), down("Camera Down"), left("Camera Left"), right("Camera Right"), zoom_in("Zoom In"), zoom_out("Zoom Out");
	
	String descriptor;
	
	KeyAction(String descriptor){
		this.descriptor = descriptor;
	}
	
	public String getDescriptor(){
		return descriptor;
	}
}
