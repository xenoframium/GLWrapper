package xenoframium.glwrapper;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.PointerBuffer;

//Monitor objects are immutable
public class GlfwMonitor {
	private static final GlfwMonitor NULL_MONITOR = new GlfwMonitor(NULL);
	
	private final long pointer;

	private GlfwMonitor(long monitorPointer) {
		pointer = monitorPointer;
	}

	public static GlfwMonitor getNullMonitor() {
		return NULL_MONITOR;
	}
	
	public static GlfwMonitor getPrimaryMonitor() {
		return new GlfwMonitor(glfwGetPrimaryMonitor());
	}

	public static GlfwMonitor[] getMonitors() {
		PointerBuffer monitorBuffer = glfwGetMonitors();
		GlfwMonitor[] monitorList = new GlfwMonitor[monitorBuffer.remaining()];

		for (int i = 0; monitorBuffer.hasRemaining(); i++) {
			monitorList[i] = new GlfwMonitor(monitorBuffer.get());
		}

		return monitorList;
	}
	
	public long getPointer() {
		return pointer;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (pointer ^ (pointer >>> 32));
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
		if (!(obj instanceof GlfwMonitor)) {
			return false;
		}
		GlfwMonitor other = (GlfwMonitor) obj;
		if (pointer != other.pointer) {
			return false;
		}
		return true;
	}

}
