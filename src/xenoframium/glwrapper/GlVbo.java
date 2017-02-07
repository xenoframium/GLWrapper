package xenoframium.glwrapper;

import static org.lwjgl.opengl.GL15.*;

//VBO objects are immutable
public class GlVbo {
	private static final GlVbo NULL_VBO = new GlVbo(0);
	
	private final GlfwWindow sharedContext;
	private final int id;
	
	private GlVbo(int id) {
		sharedContext = GlfwWindow.getNullWindow();
		this.id = id;
	}
	
	public GlVbo() {
		sharedContext = StateManager.getCurrentContext();
		id = glGenBuffers();
	}
	
	public static GlVbo getNullVBO() {
		return NULL_VBO;
	}
	
	public void bind(int bufferType) {
		if (!StateManager.getCurrentContext().equals(sharedContext)) {
			throw new IncompatibleContextException("Incompatible context when trying to bind VBO");
		}
		StateManager.bindBuffer(bufferType, this);
	}
	
	public int getId() {
		return id;
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
		if (!(obj instanceof GlVbo)) {
			return false;
		}
		GlVbo other = (GlVbo) obj;
		if (id != other.id) {
			return false;
		}
		if (!sharedContext.equals(other.sharedContext)) {
			return false;
		}
		return true;
	}

	public GlfwWindow getContext() {
		return sharedContext;
	}
}