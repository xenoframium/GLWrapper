package xenoframium.glwrapper;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

//Window objects are immutable
public class GlfwWindow {
	private static final GlfwWindow NULL_WINDOW = new GlfwWindow();

	private final GlfwWindow sharedContext;
	private final long id;

	GlfwWindow(WindowBuilder builder) {
		glfwDefaultWindowHints();
		builder.getWindowHintApplier().applyWindowHints();
		id = glfwCreateWindow(builder.getWidth(), builder.getHeight(), builder.getTitle(),
				builder.getMonitor().getPointer(), builder.getSharedContext().id);

		if (builder.getSharedContext().equals(NULL_WINDOW)) {
			sharedContext = this;
		} else {
			sharedContext = builder.getSharedContext().sharedContext;
		}
	}

	private GlfwWindow() {
		id = NULL;
		sharedContext = NULL_WINDOW;
	}

	public static GlfwWindow getNullWindow() {
		return NULL_WINDOW;
	}

	public void makeContextCurrent() {
		StateManager.makeContextCurrent(this);
	}

	public long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		if (!(obj instanceof GlfwWindow)) {
			return false;
		}
		GlfwWindow other = (GlfwWindow) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	public GlfwWindow getSharedContext() {
		return sharedContext;
	}
}
