/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library_management.dto;

/**
 *
 * @author quang
 */
public class HandledRequestDTO {
    private int handledRequestId;

    public int getHandledRequestId() {
        return handledRequestId;
    }

    public void setHandledRequestId(int handledRequestId) {
        this.handledRequestId = handledRequestId;
    }
    private int requestId;
    private int userId;
    private int bookId;
    private String requestType; 
    private String status;     

    public HandledRequestDTO(int requestId, int userId, int bookId, String requestType, String status) {
        this.requestId = requestId;
        this.userId = userId;
        this.bookId = bookId;
        this.requestType = requestType;
        this.status = status;
    }

    

    public HandledRequestDTO() {
    }

  

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
