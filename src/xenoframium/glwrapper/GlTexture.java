package xenoframium.glwrapper;

import static org.lwjgl.opengl.GL11.*;

public class GlTexture {
	private final int id;

	public GlTexture() {
		id = glGenTextures();
	}

	public int getId() {
		return id;
	}

	public void bind(int textureType) {
		StateManager.bindTexture(textureType, this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof GlTexture)) {
			return false;
		}
		GlTexture other = (GlTexture) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}
}
