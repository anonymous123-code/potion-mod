package io.github.anonymous123_code.turing_potions.api.operator;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Contract;

public class OperatorExecutionContext<T> {
	private final World world;
	private final Entity evaluatingEntity;
	private final T parameter;
	private final int recursionDepth;

	OperatorExecutionContext(World world, Entity evaluatingEntity, T parameter, int recursionDepth) {
		this.world = world;
		this.evaluatingEntity = evaluatingEntity;
		this.parameter = parameter;
		this.recursionDepth = recursionDepth;
	}

	public OperatorExecutionContext(World world, Entity evaluatingEntity, T parameter) {
		this.world = world;
		this.evaluatingEntity = evaluatingEntity;
		this.parameter = parameter;
		this.recursionDepth = 0;
	}

	public World getWorld() {
		return this.world;
	}

	public Entity getEvaluatingEntity() {
		return this.evaluatingEntity;
	}

	public T getParameter() {
		return this.parameter;
	}

	public int getRecursionDepth() {
		return this.recursionDepth;
	}

	@Contract(pure = true)
	public <S> OperatorExecutionContext<S> with(S parameter) {
		return new OperatorExecutionContext<>(this.world, this.evaluatingEntity, parameter, this.recursionDepth);
	}

	@Contract(pure = true)
	public OperatorExecutionContext<T> withAddedRecursionDepth(int recursionAddition) {
		return new OperatorExecutionContext<>(this.world, this.evaluatingEntity, this.parameter, this.recursionDepth + recursionAddition);
	}
}
