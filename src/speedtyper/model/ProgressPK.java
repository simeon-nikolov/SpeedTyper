package speedtyper.model;

import java.io.Serializable;

public class ProgressPK implements Serializable {
    protected int userId;
    protected int roomId;

    public ProgressPK() {}

    public ProgressPK(int userId, int roomId) {
        this.userId = userId;
        this.roomId = roomId;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + roomId;
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProgressPK other = (ProgressPK) obj;
		if (roomId != other.roomId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
    
    
}
