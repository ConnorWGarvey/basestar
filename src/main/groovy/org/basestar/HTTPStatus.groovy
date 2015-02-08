package org.basestar

/**
 * @since February 07, 2015
 */
enum HTTPStatus {
  ACCEPTED(202),
  OK(200);

  int code;

  HTTPStatus(int code) {
    this.code = code
  }
}