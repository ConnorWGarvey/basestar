package org.basestar

/**
 * @since February 07, 2015
 */
enum HTTPStatus {
  ACCEPTED(202),
  NO_CONTENT(204),
  OK(200)

  int code

  HTTPStatus(int code) {
    this.code = code
  }
}