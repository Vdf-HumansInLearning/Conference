CREATE TABLE speaker_session
(
    session_id UUID,
    speaker_id UUID,
    FOREIGN KEY (session_id) REFERENCES session (id),
    FOREIGN KEY (speaker_id) REFERENCES speaker (id)
);
