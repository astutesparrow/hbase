/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hbase.ipc;

import java.util.List;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.hbase.CellScannable;
import org.apache.hadoop.hbase.CellScanner;
import org.apache.hadoop.hbase.CellUtil;


import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;

/**
 * Optionally carries Cells across the proxy/service interface down into ipc. On its
 * way out it optionally carries a set of result Cell data.  We stick the Cells here when we want
 * to avoid having to protobuf them.  This class is used ferrying data across the proxy/protobuf
 * service chasm.  Used by client and server ipc'ing.
 */
@InterfaceAudience.Private
public class PayloadCarryingRpcController implements RpcController, CellScannable {
  // TODO: Fill out the rest of this class methods rather than return UnsupportedOperationException

  /**
   * They are optionally set on construction, cleared after we make the call, and then optionally
   * set on response with the result. We use this lowest common denominator access to Cells because
   * sometimes the scanner is backed by a List of Cells and other times, it is backed by an
   * encoded block that implements CellScanner.
   */
  private CellScanner cellScanner;

  public PayloadCarryingRpcController() {
    this((CellScanner)null);
  }

  public PayloadCarryingRpcController(final CellScanner cellScanner) {
    this.cellScanner = cellScanner;
  }

  public PayloadCarryingRpcController(final List<CellScannable> cellIterables) {
    this.cellScanner = CellUtil.createCellScanner(cellIterables);
  }

  /**
   * @return One-shot cell scanner (you cannot back it up and restart)
   */
  public CellScanner cellScanner() {
    return cellScanner;
  }

  public void setCellScanner(final CellScanner cellScanner) {
    this.cellScanner = cellScanner;
  }

  @Override
  public String errorText() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean failed() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isCanceled() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void notifyOnCancel(RpcCallback<Object> arg0) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void reset() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setFailed(String arg0) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void startCancel() {
    throw new UnsupportedOperationException();
  }
}