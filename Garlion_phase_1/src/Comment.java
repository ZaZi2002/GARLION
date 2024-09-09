public class Comment {
    String content;

    String response;
    User commentOwner;
    int ID;
    Comment(String content, User user){
        this.content=content;
        this.response=null;
        this.commentOwner=user;
        int id = Garlion.AllFoods.size()+1;
        this.ID=id;
    }
    void setResponse(String response){this.response=response;}
    void getCommentResponse(){
        if(this.response==null) {
            System.out.println("there is no response for this comment.");
        }
        else{
            System.out.println("response : "+this.response);
        }
    }
    void editContent(String newContent){this.content=newContent;System.out.println("comment edited successfully.");}
}
