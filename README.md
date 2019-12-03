# voucherpool

Introduction:
A voucher pool is a collection of (voucher) codes that can be used by customers (recipients) to get discounts in a web shop. Each code may only be used once and we would like to know when it was used by the recipient. Since there can be many recipients in a voucher pool, we need a call that auto-generates voucher codes for each recipient.

Requirement:

- For a given Special Offer and an expiration date, generate for each Recipient a Voucher Code.

- Provide an endpoint, reachable via HTTP, which receives a Voucher Code and Email and validates the Voucher Code. In Case it is valid, return the Percentage Discount and set the date of usage.

- Extra: For a given Email, return all his valid Voucher Codes with the Name of the Special Offer.
