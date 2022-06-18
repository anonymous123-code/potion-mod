package io.github.anonymous123_code.turing_potions.datadriven;

import io.github.anonymous123_code.turing_potions.api.HeatSource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.quiltmc.qsl.resource.loader.api.reloader.SimpleResourceReloader;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * @author anonymous123-code
 */
public class HeatSourceResourceLoader implements SimpleResourceReloader<List<HeatSource>> {
	private final Identifier quiltId;

	public HeatSourceResourceLoader(Identifier quiltId) {
		this.quiltId = quiltId;
	}

	@Override
	public CompletableFuture<List<HeatSource>> load(ResourceManager manager, Profiler profiler, Executor executor) {
		return null;
	}

	@Override
	public CompletableFuture<Void> apply(List<HeatSource> data, ResourceManager manager, Profiler profiler, Executor executor) {
		return null;
	}

	@Override
	public Identifier getQuiltId() {
		return this.quiltId;
	}
}
