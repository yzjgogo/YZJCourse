package com.yin.yzjcourse.DesignModel.P9_2组合模式_由迭代器模式演变而来.C3节点迭代器;

import com.yin.yzjcourse.DesignModel.P9_2组合模式_由迭代器模式演变而来.C5叶节点迭代器.NullIterator;
import com.yin.yzjcourse.DesignModel.P9_2组合模式_由迭代器模式演变而来.C1节点和叶节点的超类.MenuComponent;

import java.util.*;

/**
 * 注意：这个迭代器用到了递归，是整个组合模式的难点，现在已经看懂了，看看注释再琢磨琢磨就看懂了。
 *
 * 前提“这是针对一个节点的迭代器，注意这个节点本身就是一个树，可能有很多子节点和子叶节点，所以要用到递归遍历。
 * 根据前提，遍历的过程应该是这样的，递归遍历，先将当前节点的迭代器放入栈顶，挨个遍历当前节点的子元素(子节点或子叶节点)，遍历到谁(A)，就应该针对谁(A)再展开遍历，将(A)的迭代器放到栈顶，
 * 因为(A)本身就是一个MenuComponent也是有子元素的，也需要遍历，A这样一层层递归遍完后，A的迭代器会从栈顶离开(注意，遍历A时，可能又遇到A的子节点A1，又会将A1的迭代器放到栈顶，这样递归往复)，
 * 就这样又到B，C...等等，当前节点的迭代器又回到栈顶，继续等等。可见这是一个递归操作。
 *
 * 注意一点，节点(Menu)内部有一个集合维护元素(因为节点是有子节点或子叶节点的)，因此节点有迭代器很好理解，但是叶节点虽然是末梢的节点，没有子节点或子叶节点，为了和节点保持一致，避免客户代码使用时
 * 还要判断是否是叶节点等等，也给也节点(MenuItem)定义了一个迭代器{@link NullIterator},只是这个迭代器的hasNext()永远返回false，next()永远返回null，因此我们称其为空迭代器。
 *
 * 知道上面注意的一点后，当遍历到叶节点时，叶节点的迭代器NullIterator一会被放到栈顶，只是该迭代器一旦被调用，hasNext()返回false直接从栈顶弹出。
 */
public class CompositeIterator implements Iterator<MenuComponent> {
    /**
     * stack是一个栈,用于存放当前正在使用的迭代器，无论是peek()，pop()，push()都是针对栈顶元素的操作。
     */
	Stack<Iterator<MenuComponent>> stack = new Stack<Iterator<MenuComponent>>();
   
	public CompositeIterator(Iterator<MenuComponent> iterator) {
		stack.push(iterator);
	}

	@Override
	public MenuComponent next() {
		if (hasNext()) {
			Iterator<MenuComponent> iterator = stack.peek();
            //取出的这个节点可能是另一个节点或叶节点,节点有迭代器CompositeIterator，叶节点有迭代器NullIterator，NullIterator和CompositeIterator
            //都是抽象的Iterator<MenuComponent>类型。因此取出这个next后，要把它的迭代器放到栈顶去遍历它，同时将这个next返回,参考hasNext()会发现该next一旦被遍历完,就会从栈顶弹出。
			MenuComponent component = iterator.next();
			stack.push(component.createIterator());
			return component;
		} else {
			return null;
		}
	}

	@Override
	public boolean hasNext() {
		if (stack.empty()) {//整个跟节点里面的所有节点和叶节点全部遍历完毕
			return false;
		} else {
			Iterator<MenuComponent> iterator = stack.peek();
			if (!iterator.hasNext()) {//说明当前节点或叶节点遍历完毕，因此其迭代器可以从栈顶移除了。
				stack.pop();
				return hasNext();//再访问一次栈，因为移除一个栈顶后可能是回到某个迭代器，也可能是栈内的所有迭代器都用完了。
			} else {
				return true;
			}
		}
	}
	
	/*
	 * No longer needed as of Java 8
	 * 
	 * (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 *
	public void remove() {
		throw new UnsupportedOperationException();
	}
	*/
}


