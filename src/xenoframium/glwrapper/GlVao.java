package xenoframium.glwrapper;

import static org.lwjgl.opengl.GL30.*;

//VAO objects are immutable
public class GlVao {
	private static final GlVao NULL_VAO = new GlVao(0);
	
	private final GlfwWindow sharedContext;
	private final int id;
	
	private GlVao(int id) {
		sharedContext = GlfwWindow.getNullWindow();
		this.id = id;
	}
	
	public GlVao() {
		sharedContext = StateManager.getCurrentContext();
		id = glGenVertexArrays();
	}
	
	public static GlVao getNullVAO() {
		return NULL_VAO;
	}
	
	public void bind() {
		if (!StateManager.getCurrentContext().equals(sharedContext)) {
			throw new IncompatibleContextException("Incompatible context when trying to bind VAO");
		}
		StateManager.bindVertexArray(this);
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
		if (!(obj instanceof GlVao)) {
			return false;
		}
		GlVao other = (GlVao) obj;
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
