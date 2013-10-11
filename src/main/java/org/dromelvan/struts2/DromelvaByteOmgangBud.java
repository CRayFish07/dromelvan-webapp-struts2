package org.dromelvan.struts2;

import org.dromelvan.struts2.util.ListadSpelareBudCollection;


public class DromelvaByteOmgangBud extends DromelvaByten {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2570519775953242109L;
	private ListadSpelareBudCollection listadeSpelareBud;
    
    public ListadSpelareBudCollection getListadeSpelareBud() {
        if(listadeSpelareBud == null) {            
            listadeSpelareBud = new ListadSpelareBudCollection(getDeByteOmgang());
        }
        return listadeSpelareBud;
    }
    
}
