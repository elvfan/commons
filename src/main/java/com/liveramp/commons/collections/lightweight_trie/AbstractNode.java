/**
 *  Copyright 2013 Liveramp
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.liveramp.commons.collections.lightweight_trie;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

abstract class AbstractNode<V> {
  protected V value;

  protected AbstractNode(V value) {
    this.value = value;
  }

  public V getValue() {
    return value;
  }

  public abstract V get(char[] searchArr, int startOffset);

  public abstract AbstractNode<V>[] getChildren();

  public abstract char[] getPrefix();

  public abstract char getPrefixFirst();

  @Override
  public String toString() {
    return getClass().getSimpleName() + " <getPrefix()=" + Arrays.toString(getPrefix())
        + ", value=" + value + ", getChildren()="
        + Arrays.toString(getChildren()) + ">";
  }

  public abstract void getPartialMatches(Set<String> partialMatches, char[] searchArr, int searchArrOffset);

  public void getNodeAnalysis(List<String> results, int depth) {
    int childrenLength = 0;
    if (getChildren() != null) {
      childrenLength = getChildren().length;
    }
    int prefixLength = 0;
    if (getPrefix() != null) {
      prefixLength = getPrefix().length;
    }
    results.add(this.getClass().getSimpleName() + " " + childrenLength + " " + prefixLength + " " + depth);
    if (getChildren() != null) {
      for (AbstractNode<V> child : getChildren()) {
        child.getNodeAnalysis(results, depth + 1);
      }
    }
  }
}
