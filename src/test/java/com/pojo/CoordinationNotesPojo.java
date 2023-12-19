package com.pojo;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Detail")
public class CoordinationNotesPojo {

    @XmlElement(name = "Entered_By")
    private String enteredBy;


    @XmlElement(name = "clog_note")
    private String clogNote;


    @XmlElement(name = "Coord_Note_Date")
    private String coordNoteDate;

    @XmlElement(name = "Note_Type")
    private String noteType;

    @XmlElement(name = "Note_Status")
    private String noteStatus;





        public String getEnteredBy() {
            return enteredBy;
        }
        public void setEnteredBy(String enteredBy) {
            this.enteredBy = enteredBy;
        }
        public String getClogNote() {
            return clogNote;
        }
        public void setClogNote(String clogNote) {
            this.clogNote = clogNote;
        }
        public String getCoordNoteDate() {
            return coordNoteDate;
        }
        public void setCoordNoteDate(String coordNoteDate) {
            this.coordNoteDate = coordNoteDate;
        }
        public String getNoteType() {
            return noteType;
        }
        public void setNoteType(String noteType) {
            this.noteType = noteType;
        }
        public String getNoteStatus() {
            return noteStatus;
        }
        public void setNoteStatus(String noteStatus) {
            this.noteStatus = noteStatus;
        }
    }

