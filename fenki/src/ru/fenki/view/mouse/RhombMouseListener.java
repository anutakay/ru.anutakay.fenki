package ru.fenki.view.mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ru.fenki.view.ClickHandlerInterface;

public class RhombMouseListener implements MouseListener {
	
	private ClickHandlerInterface mHandler;
	
	public void setClickHandler(ClickHandlerInterface handler){
		mHandler = handler;
	}
	
	public ClickHandlerInterface getClickHandler(){
		return mHandler;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		//System.out.println("mouseClicked " + event.getX() + "x" + event.getY());
		if(mHandler!=null){
			mHandler.onClicked(event.getX(), event.getY());
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		//System.out.println("mouseEntered");
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		//System.out.println("mouseExited");
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		//System.out.println("mousePressed");
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		//System.out.println("mouseReleased");
	}

}
