package io.github.knt5;

import java.io.InputStream;

public interface ArgumentResolver {
	public Argument resolve(InputStream stream);
}
