package org.example.model;

public class TotalOrderNotes extends OrderNotes{
    private Long totalNotes;

    public TotalOrderNotes(Integer idNotes, String notes, String name, Long totalNotes) {
        super(idNotes, notes, name);
        this.totalNotes = totalNotes;
    }

    public Long getTotalNotes() {
        return totalNotes;
    }

    public void setTotalNotes(Long totalNotes) {
        this.totalNotes = totalNotes;
    }
}
