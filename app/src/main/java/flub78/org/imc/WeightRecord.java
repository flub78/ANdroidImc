package flub78.org.imc;

/**
 * Created by flub78 on 2021-03-03.
 */
public class WeightRecord {

    // Notez que l'identifiant est un long
    private long mId;
    private float mWeight;
    private float mSize;
    private String mUser;
    private String mDate;
    private String mComment;


    public WeightRecord() {
        super();
    }

    public WeightRecord(long id, String user, float weight, float size, String date, String comment) {
        super();
        this.mId = id;
        this.mWeight = weight;
        this.mSize = size;
        this.mUser = user;
        this.mDate = date;
        this.mComment = comment;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public float getWeight() {
        return mWeight;
    }

    public void setWeight(float weight) {
        mWeight = weight;
    }

    public float getSize() {
        return mSize;
    }

    public void setSize(float size) {
        mSize = size;
    }

    public String getUser() {
        return mUser;
    }

    public void setUser(String user) {
        mUser = user;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public String toString () {
        return "WeightRecord: id=" + mId +
                ", user=" + mUser +
                ", weight=" + mWeight +
                ", size=" + mSize +
                ", date=" + mDate +
                ", comment=" + mComment;
    }
}
