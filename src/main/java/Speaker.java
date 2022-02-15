public class Speaker {
    private Participant participant;

    private String title;
    private String company;
    private String linkedinAcc;
    private String twitterAcc;
    private String githubAcc;
    private String biography;

    public Speaker(Participant participant, String title, String company, String linkedinAcc, String twitterAcc, String githubAcc, String biography) {
        this.participant = participant;
        this.title = title;
        this.company = company;
        this.linkedinAcc = linkedinAcc;
        this.twitterAcc = twitterAcc;
        this.githubAcc = githubAcc;
        this.biography = biography;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLinkedinAcc() {
        return linkedinAcc;
    }

    public void setLinkedinAcc(String linkedinAcc) {
        this.linkedinAcc = linkedinAcc;
    }

    public String getTwitterAcc() {
        return twitterAcc;
    }

    public void setTwitterAcc(String twitterAcc) {
        this.twitterAcc = twitterAcc;
    }

    public String getGithubAcc() {
        return githubAcc;
    }

    public void setGithubAcc(String githubAcc) {
        this.githubAcc = githubAcc;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
